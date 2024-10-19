import { Component, OnInit, HostListener } from '@angular/core';

@Component({
  selector: 'app-snow',
  standalone: true,
  template: '<canvas id="snowCanvas"></canvas>',
  styles: [`
    #snowCanvas {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      pointer-events: none; /* Ne pas interférer avec les clics */
    }
  `]
})
export class SnowComponent implements OnInit {
  private canvas!: HTMLCanvasElement;
  private ctx!: CanvasRenderingContext2D;
  private snowflakes: { x: number; y: number; size: number; density: number; direction: number; isBlurred: boolean; shape: string }[] = [];

  ngOnInit() {
    this.canvas = document.getElementById('snowCanvas') as HTMLCanvasElement;
    this.ctx = this.canvas.getContext('2d')!;
    this.resizeCanvas();
    this.createSnowflakes();
    this.animate();
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.resizeCanvas();
  }

  private resizeCanvas() {
    this.canvas.width = window.innerWidth;
    this.canvas.height = window.innerHeight;
  }

  private createSnowflakes() {
    const numSnowflakes = 100; // Nombre de flocons de neige
    for (let i = 0; i < numSnowflakes; i++) {
      this.snowflakes.push({
        x: Math.random() * this.canvas.width,
        y: Math.random() * this.canvas.height,
        size: Math.random() * 10 + 5, // Augmenter la taille des flocons (entre 5 et 15)
        density: Math.random() * 1.5 + 1, // Densité pour une chute plus rapide
        direction: Math.random() < 0.5 ? -3 : 3, // Direction aléatoire (gauche ou droite)
        isBlurred: Math.random() < 0.5, // 50% de chance d'être flou
        shape: ['square', 'rectangle', 'triangle', 'circle'][Math.floor(Math.random() * 4)] // Ajouter cercle
      });
    }
  }

  private animate() {
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height); // Effacer le canevas

    for (let snowflake of this.snowflakes) {
      this.ctx.beginPath();

      // Dessiner selon la forme choisie
      if (snowflake.shape === 'square') {
        this.ctx.rect(snowflake.x - snowflake.size / 2, snowflake.y - snowflake.size / 2, snowflake.size, snowflake.size); // Carré
      } else if (snowflake.shape === 'rectangle') {
        this.ctx.rect(snowflake.x - snowflake.size / 2, snowflake.y - snowflake.size / 2, snowflake.size, snowflake.size * 0.5); // Rectangle
      } else if (snowflake.shape === 'triangle') {
        this.ctx.moveTo(snowflake.x, snowflake.y - snowflake.size); // Sommet du triangle
        this.ctx.lineTo(snowflake.x - snowflake.size / 2, snowflake.y + snowflake.size / 2); // Bas gauche
        this.ctx.lineTo(snowflake.x + snowflake.size / 2, snowflake.y + snowflake.size / 2); // Bas droit
        this.ctx.closePath(); // Fermer le chemin pour le triangle
      } else if (snowflake.shape === 'circle') {
        this.ctx.arc(snowflake.x, snowflake.y, snowflake.size / 2, 0, Math.PI * 2); // Cercle
      }

      // Appliquer des couleurs et opacités différentes selon le flocon
      if (snowflake.isBlurred) {
        this.ctx.fillStyle = '#ffede0'; // Couleur floue en bleu
        this.ctx.shadowColor = 'rgba(255, 255, 255, 0.7)'; // Couleur de l'ombre en bleu
        this.ctx.shadowBlur = 10; // Intensité du flou
      } 
      this.ctx.fill();

      // Mettre à jour la position des flocons de neige
      snowflake.y += snowflake.density; // Vitesse de chute

      // Déplacement latéral
      snowflake.x += snowflake.direction * (Math.random() * 0.5); // 0.5 pour le mouvement latéral

      // Réinitialiser le flocon de neige au sommet s'il sort de l'écran
      if (snowflake.y > this.canvas.height) {
        snowflake.y = 0;
        snowflake.x = Math.random() * this.canvas.width;
        // Réinitialiser la direction aléatoire
        snowflake.direction = Math.random() < 0.5 ? -1 : 1; // Direction aléatoire
      }

      // Garder les flocons de neige à l'intérieur de l'écran
      if (snowflake.x < 0) {
        snowflake.x = 0;
      } else if (snowflake.x > this.canvas.width) {
        snowflake.x = this.canvas.width;
      }
    }

    requestAnimationFrame(() => this.animate());
  }
}
