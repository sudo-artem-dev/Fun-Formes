import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router'; 
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resultat',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './resultat.component.html',
  styleUrl: './resultat.component.css'
})
export class ResultatComponent {
  scoreFacile: number = 0;
  scoreDifficile: number = 0;
  niveau: string = '';

  constructor(    
    private dialogRef: MatDialogRef<ResultatComponent>, 
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { scoreFacile: number, scoreDifficile: number, niveau: string }
  ) {
      this.scoreFacile = data?.scoreFacile ?? 0;
      this.scoreDifficile = data?.scoreDifficile ?? 0;
      this.niveau = data?.niveau ?? ''; 
      // console.log('Données reçues dans ResultatComponent:', this.scoreFacile, this.scoreDifficile);
    }

  goToHome() {
    // Navigation avec les scores facile et difficile passés dans les queryParams
    this.router.navigate(['/accueil'], { 
      queryParams: { scoreFacile: this.scoreFacile, scoreDifficile: this.scoreDifficile } 
    }); 
  }

  close(): void {
    this.dialogRef.close();
  }
}
