import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http'; 
import { Router } from '@angular/router';
import { ResultatComponent } from '../resultat/resultat.component';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
})
export class ConfirmationComponent {
  niveau: number;
  id: number;
  scoreFacile: number;
  scoreDifficile: number;
  dessinsTermines: number;

  constructor(
    private dialogRef: MatDialogRef<ConfirmationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { niveau: number; id: number; scoreFacile: number; scoreDifficile: number; dessinsTermines: number },
    private http: HttpClient,
    private router: Router,
    private dialog: MatDialog
  ) {
    this.niveau = data.niveau;
    this.id = data.id;
    this.scoreFacile = data?.scoreFacile ?? 0; // Utilisation du score facile
    this.scoreDifficile = data?.scoreDifficile ?? 0; // Utilisation du score difficile
    this.dessinsTermines = data.dessinsTermines;
  }

  close(): void {
    this.dialogRef.close();
  }

  onPass() {
    const currentId = Number(this.id);
    const nextId = currentId + 1; // Passe au dessin suivant
    this.dessinsTermines++;

    // Vérifie si le dessin suivant existe
    this.http.get<any>(`http://localhost:8080/api/dessins/niveau/${this.niveau}/${nextId}`)
    .subscribe(
      data => {
        if (data) {
          this.router.navigate([`/niveau/${this.niveau}/${nextId}`], {
            queryParams: { dessinsTermines: this.dessinsTermines } 
          });
          this.dialogRef.close(); // Ferme le pop-up après la navigation
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

  terminerJeu(): void {
    this.dialogRef.close(); // Ferme le pop-up de confirmation 
    this.dialog.open(ResultatComponent, {
      data: {niveau : this.niveau, scoreFacile: this.scoreFacile, scoreDifficile: this.scoreDifficile }, // Passez les scores ici
      width: 'auto', 
      height: 'auto',
    });
    console.log('Le jeu est terminé avec un score facile de:', this.scoreFacile, 'et un score difficile de:', this.scoreDifficile);
  }
}
