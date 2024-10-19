import { Component, OnInit, ViewChildren, ElementRef, AfterViewInit, QueryList } from '@angular/core'; 
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router'; 
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ConfirmationComponent } from '../confirmation/confirmation.component';
import { CorrectionComponent } from '../correction/correction.component';
import { ResultatComponent } from '../resultat/resultat.component';



@Component({
    selector: 'app-niveau',
    standalone: true,
    templateUrl: './niveau.component.html',
    styleUrls: ['./niveau.component.css'],
    imports: [CommonModule, HttpClientModule, ReactiveFormsModule, MatDialogModule]
})
export class NiveauComponent implements OnInit, AfterViewInit {


    canvases: HTMLCanvasElement[] = []; // Déclaration de la propriété canvases
    @ViewChildren('dessinCanvas') dessinCanvases!: QueryList<ElementRef<HTMLCanvasElement>>;
    scoreFacile: number = 0;
    scoreDifficile: number = 0;
    niveau: string = '';
    id: string = ''; // une propriété pour l'ID
    private scores: number[] = [];
    private dessinsReussis: number = 0;
    dessinsTermines: number = 0; // Initialiser le compteur de dessins terminés
    figureForm!: FormGroup; // '!' pour indiquer qu'il sera initialisé plus tard
    popupCorrectionOuvert: boolean = false;
    popupPasserOuvert: boolean = false;
    dessins: { 
        nom: string;
        totalFormes: number;
        nombreParType: { 
            cercle: number;
            carre: number;
            rectangle: number;
            triangle: number;
        };
        formes: {
            type: string;
            couleur?: string;
            longueur?: number;
            largeur?: number;
            coteA?: number;
            coteB?: number;
            coteC?: number;
            rayon?: number;
            cote?: number;
            x: number;
            y: number;
        }[];

        perimetreTotal: number;
        aireTotal: number;
    }[] = [];
    

    constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient, private fb: FormBuilder,private dialog: MatDialog) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.niveau = params['niveau'];
            this.id = params['id'];
            this.fetchDessins();
        });
        
        // Initialisez figureForm ici
        this.figureForm = this.fb.group({
            triangle: [null],  // Valeur initiale null (vide)
            cercle: [null],    
            rectangle: [null], 
            carre: [null]      
        });

        this.route.queryParams.subscribe(params => {
            this.dessinsTermines = +params['dessinsTermines'] || 1;
            // console.log('Nombre de dessins terminés:', this.dessinsTermines);
          });
    }

    ngAfterViewInit() {
        this.dessinCanvases.changes.subscribe(() => {
            this.dessiner();
        });
    }

    fetchDessins() {
        this.http.get<any>(`http://localhost:8080/api/dessins/niveau/${this.niveau}/${this.id}`)
            .subscribe(
                data => {
                // Vérifiez la structure des données ici
                // console.log('Données brutes récupérées:', data);
                    // Assurez-vous que les données sont un tableau
                    this.dessins = Array.isArray(data) ? data : [data];
                    

                    this.dessins.forEach(dessin => {
                        dessin.nombreParType = dessin.nombreParType ;
                       // console.log('nombreParType pour ce dessin:', dessin.nombreParType);
                    });
                    // console.log('Données récupérées:', this.dessins);
                    // console.log('Nombre de dessins:', this.dessins.length);
                },
                error => {
                    console.error('Erreur lors de la récupération des dessins :', error);
                }
            );
    }
    

    dessiner() {
        this.dessinCanvases.forEach((dessinCanvas: ElementRef<HTMLCanvasElement>, index: number) => {
            const ctx = dessinCanvas.nativeElement.getContext('2d');
            const dessin = this.dessins[index]; // Récupérer le dessin correspondant

            if (ctx && dessin) {
                ctx.clearRect(0, 0, dessinCanvas.nativeElement.width, dessinCanvas.nativeElement.height);

                dessin.formes.forEach((forme) => {
                    ctx.fillStyle = forme.couleur || 'black';

                    switch (forme.type.toLowerCase()) {
                        case 'rectangle':
                            ctx.fillRect(forme.x, forme.y, forme.longueur || 50, forme.largeur || 50);
                            break;

                        case 'triangle':
                            if (forme.coteA !== undefined && forme.coteB !== undefined) {
                                ctx.beginPath();
                                ctx.moveTo(forme.x, forme.y); // Point de départ
                                ctx.lineTo(forme.x + forme.coteA, forme.y); // Base du triangle
                                ctx.lineTo(forme.x + (forme.coteA / 2), forme.y - forme.coteB); // Sommet
                                ctx.closePath();
                                ctx.fill();
                            }
                            break;

                        case 'cercle':
                            const rayon = forme.rayon ?? 50; // Vérifie que le rayon est défini avant d'essayer de dessiner
                            ctx.beginPath();
                            ctx.arc(forme.x, forme.y, rayon, 0, Math.PI * 2);
                            ctx.fill();
                            break;

                        case 'carre':
                            const cote = forme.cote ?? 50; // Un carré est un rectangle avec des côtés égaux
                            ctx.fillRect(forme.x, forme.y, cote, cote);
                            break;

                        default:
                            console.warn(`Type de forme non reconnu : ${forme.type}`);
                    }
                });
            } else {
                console.error('Erreur: Contexte du canvas non disponible ou dessin non trouvé.');
            }
        });
    }

    onSubmit() {
        if (this.figureForm.valid) {
            if (this.dessins.length === 0) {
                console.error('Aucun dessin disponible.');
                return;
            }
    
            const dessinActuel = this.dessins[0];
    
            if (!dessinActuel.nombreParType) {
                console.error('nombreParType n\'est pas défini pour ce dessin.');
                return;
            }
    
            const nombreAttendu: { [key: string]: number } = dessinActuel.nombreParType;
            //console.log('nombreAttendu :', nombreAttendu); // Log pour vérifier les attentes
    
            const valeursSoumises = this.figureForm.value;
            console.log('valeursSoumises :', valeursSoumises); // Log pour vérifier les valeurs soumises
    
            let toutesCorrespondent = true;
    
            // Compare les valeurs soumises avec celles attendues pour ce dessin
            for (const [key, value] of Object.entries(valeursSoumises)) {
                const keyNormalized = key.charAt(0).toUpperCase() + key.slice(1); // la première lettre en majuscule
                //console.log(`Clé normalisée : ${keyNormalized}, valeur soumise : ${value}`); // Log pour voir chaque clé/valeur
    
                if (nombreAttendu[keyNormalized] !== undefined) {
                    const isCorrect = Number(value) === nombreAttendu[keyNormalized];
                    //console.log(`Comparaison pour ${keyNormalized}: attendu = ${nombreAttendu[keyNormalized]}, soumis = ${value}, correct = ${isCorrect}`);
    
                    // Si une seule figure est incorrecte, on marque l'ensemble comme incorrect
                    if (!isCorrect) {
                        toutesCorrespondent = false;
                        break; // Sortie immédiate dès qu'une erreur est détectée
                    }
                }
            }
    
            // Attribue un score de 1 si toutes les figures correspondent, sinon 0
            const score = toutesCorrespondent ? 1 : 0;
            this.scores.push(score); // Stocke le score (1 ou 0) pour ce dessin
            console.log('Score calculé pour ce dessin:', score);
    
            // Si toutes les réponses sont correctes
            if (toutesCorrespondent) {
                // console.log('Tous les nombres correspondent! Score: 1');
                this.dessinsReussis += 1; // Incrémente le nombre de dessins réussis
                this.dessinSuivant(); // Passe au dessin suivant
            } else {
                this.openPopupCorrection(); // Afficher la popup en cas d'échec
                //console.log('Nombre par Type incorrect. Score pour ce dessin: 0');
            }
    
            this.figureForm.reset();
        }
    }
    
    
    // Méthode pour calculer et afficher le score final
    calculerScoreFinal() {
        if (this.niveau === 'facile') {
            this.scoreFacile = this.scores.reduce((acc, val) => acc + val, 0);
        } else if (this.niveau === 'difficile') {
            this.scoreDifficile = this.scores.reduce((acc, val) => acc + val, 0);
        }

        return {
            scoreFacile: this.scoreFacile,
            scoreDifficile: this.scoreDifficile,
        };
    }

    
  
    dessinSuivant(){
        const currentId = Number(this.id); 
        const nextId = currentId + 1; // Passe au dessin suivant
        this.dessinsTermines++;

        // Vérifie si le dessin suivant existe
        this.http.get<any>(`http://localhost:8080/api/dessins/niveau/${this.niveau}/${nextId}`)
        .subscribe(
          data => {
            // console.log('Données reçues de l\'API:', data);
            if (data) {
                this.router.navigate([`/niveau/${this.niveau}/${nextId}`], {
                    queryParams: { dessinsTermines: this.dessinsTermines } 
                  });
                console.log(this.dessinsTermines , '/ 5')
            } else {
              console.log('Aucun dessin suivant trouvé dans ce niveau.');
            }
          },
          error => {
            this.terminerJeu();
            console.error('Erreur lors de la vérification du dessin suivant :', error);
          }
        );
    }
    openPopupCorrection(): void {
        // console.log('Ouverture du popup de confirmation avec les données :');
        // console.log('Niveau:', this.niveau);
        // console.log('ID:', this.id);
        if (this.popupCorrectionOuvert) {
            return; // Ne rien faire si le popup est déjà ouvert
        }
        this.popupCorrectionOuvert = true; // Marquez le popup comme ouvert
        const scoreDetails = this.calculerScoreFinal(); // Récupère les détails du score

        const dialogRef = this.dialog.open(CorrectionComponent, {
            data: { niveau: this.niveau, id: this.id, scoreFacile: scoreDetails.scoreFacile, scoreDifficile: scoreDetails.scoreDifficile, dessinsTermines: this.dessinsTermines }, // passe les données ici
            width: 'auto', 
            height: 'auto',
        });
        // Réinitialisez l'état lorsque le popup est fermé
        dialogRef.afterClosed().subscribe(() => {
            this.popupCorrectionOuvert = false; 
        });
    }
    openPopupPasser(): void {
        if (this.popupPasserOuvert) {
            return; // Ne rien faire si le popup est déjà ouvert
        }
        this.popupPasserOuvert = true; // Marquer le popup comme ouvert
        const scoreDetails = this.calculerScoreFinal(); // Récupère les détails du score

        const dialogRef = this.dialog.open(ConfirmationComponent, {
            data: { niveau: this.niveau, id: this.id, scoreFacile: scoreDetails.scoreFacile, scoreDifficile: scoreDetails.scoreDifficile, dessinsTermines: this.dessinsTermines }, // passe les données ici
            width: 'auto', 
            height: 'auto',
        });
        dialogRef.afterClosed().subscribe(() => {
            this.popupPasserOuvert = false; 
        });
    }
    
    terminerJeu() : void {
        const scoreDetails = this.calculerScoreFinal(); // Récupère les détails du score
        this.dialog.open(ResultatComponent, { 
            data: { niveau: this.niveau, scoreFacile: scoreDetails.scoreFacile, scoreDifficile: scoreDetails.scoreDifficile, },
            width: 'auto', 
            height: 'auto',
      });   

        console.log('Le jeu est terminé.');
    
    }
    goToHome() {
        this.router.navigate(['/accueil']); 
      }
}
