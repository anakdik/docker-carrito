import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logueado = false;

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    
    this.logueado = this.auth.isLogged();
  }

  logout() {
    this.auth.logout();
    this.logueado = false;
    this.router.navigate(['/login']);
  }
}
