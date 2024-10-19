import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SnowComponent } from './snow/snow.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SnowComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front-fun_formes';
}
