import {
  Component,
  OnInit,
} from '@angular/core';
import { SharedService } from '../shared/shared.service';
import { MenuItem, PrimeIcons } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(private sharedService: SharedService){} 
  isLoggedIn!: boolean;
  items!: MenuItem[];
  ngOnInit() {
      this.sharedService.loginStatus$.subscribe((status) => {
        this.isLoggedIn = status;
      });
  
    
      this.items = [
          {
              label: 'Home',
              icon: PrimeIcons.HOME,
<<<<<<< Updated upstream
              routerLink: '/'
=======
              routerLink:"/"
>>>>>>> Stashed changes
          },
          {
              label: 'About',
              icon: PrimeIcons.EXCLAMATION_CIRCLE,
              routerLink: "/about"
          },{
            label: 'Services',
            icon: PrimeIcons.SERVER,
        },
        
      {
        label: 'Contact Us',
        icon: PrimeIcons.PHONE
    }
      ];


}
}