import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PrimeIcons, MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { SignupComponent } from '../auth/signup/signup.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  items!: MenuItem[];

  ngOnInit() {
      this.items = [
          {
              label: 'Home',
              icon: PrimeIcons.HOME,
          },
          {
              label: 'About',
              icon: PrimeIcons.EXCLAMATION_CIRCLE
          },{
            label: 'Services',
            icon: PrimeIcons.SERVER,
        },
        {
            label: 'Login',
            icon: PrimeIcons.USER
            
        },
        {
          label: 'Signup',
          icon: PrimeIcons.USER,
          routerLink : "/signup"
      },
      {
        label: 'Contact Us',
        icon: PrimeIcons.PHONE
    }
      ];
  }
}