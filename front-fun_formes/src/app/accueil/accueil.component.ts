import { Component, OnInit } from '@angular/core'; 
import { Router } from '@angular/router'; 
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {
  scoreFacile: number = 0;
  scoreDifficile: number = 0;

  constructor(private router: Router, private dialog: MatDialog,private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.scoreFacile = +params['scoreFacile'] || 0; // Récupérer le score pour le niveau facile
      this.scoreDifficile = +params['scoreDifficile'] || 0; // Récupérer le score pour le niveau difficile
    });
    // Ferme tous les dialogues ouverts lorsque le composant est initialisé
    const dialogRef = this.dialog.openDialogs; // Récupérer tous les dialogues ouverts
    dialogRef.forEach((dialog) => dialog.close()); // Ferme chaque dialogue
  }

  goToRegles() {
    this.router.navigate(['/regles']); 
  }

  goToAdmin() {
    this.router.navigate(['/admin']); 
  }

  goToNiveau(niveau: string, id: string) {
    this.router.navigate(['/niveau', niveau, id]);
  }
}
