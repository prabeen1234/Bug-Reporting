import {
  Component,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { SharedService } from '../shared/shared.service';
import { MenuItem, PrimeIcons } from 'primeng/api';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NavbarService } from '../auth/service/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit,OnDestroy {
  constructor(private sharedService: SharedService,private router :Router,private navbarService:NavbarService){
    this.subscription =this.navbarService.showNavbar.subscribe((value)=>{
      this.showNavbar=value;
    });
  } 
  showNavbar!: boolean;
  subscription:Subscription;
   selectedOption!: string;
  isLoggedIn: boolean=false;
  items!: MenuItem[];
  ngOnInit() {
      this.sharedService.loginStatus$.subscribe((status) => {
        this.isLoggedIn = status;
      });
  
    
      this.items = [
          {
              label: 'Home',
              icon: PrimeIcons.HOME,
              routerLink: '/'

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
logout() {
  
  this.isLoggedIn = false;
  this.router.navigate(['/login']);
}
ngOnDestroy(): void {
  this.subscription.unsubscribe;

}

}