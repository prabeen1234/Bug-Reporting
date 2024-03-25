import { Component, OnDestroy, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { AdminBugDto, BugDto } from 'src/app/user';
import { ToastrService } from 'ngx-toastr';
import { FooterService } from 'src/app/auth/service/footer.service';
import { Subscription } from 'rxjs';
import {  NavigationEnd, Router } from '@angular/router';
import { AdminbarService } from './adminbar.service';
import { SharedService } from 'src/app/shared/shared.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent  implements OnDestroy {
  showadminBar!:boolean;
  subscription:Subscription;
  isLoggedIn: boolean=false;

  constructor(private adminbarService:AdminbarService,private sharedService:SharedService,private router:Router,private footerService : FooterService , private navbarService:NavbarService){

    this.subscription = adminbarService.showadminBar.subscribe((value)=>{
      this.showadminBar = value;
    });
  }
  ngOnInit() {
    this.sharedService.loginStatus$.subscribe((status) => {
      this.isLoggedIn = status;
    });
    this.navbarService.hide();
    this.footerService.hide();


    
}
ngOnDestroy(): void {
  this.subscription.unsubscribe;
  this.adminbarService.show();
}

  logout() {
  
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }


}
