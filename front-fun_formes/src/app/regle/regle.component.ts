import { Component } from '@angular/core';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-regle',
  standalone: true,
  imports: [],
  templateUrl: './regle.component.html',
  styleUrl: './regle.component.css'
})
export class RegleComponent {
  constructor(private router: Router) {}

  goToHome() {
    this.router.navigate(['/accueil']); 
  }
}
