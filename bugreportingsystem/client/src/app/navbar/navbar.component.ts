import { Component } from '@angular/core';
import { PrimeIcons, MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';

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
          icon: PrimeIcons.USER
      },{
        label: 'Contact Us',
        icon: PrimeIcons.PHONE
    }
      ];
  }
}