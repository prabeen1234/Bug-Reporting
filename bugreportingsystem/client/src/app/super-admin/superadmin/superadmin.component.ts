import { Component, OnDestroy, OnInit } from '@angular/core';
import { FooterService } from 'src/app/auth/service/footer.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';

@Component({
  selector: 'app-superadmin',
  templateUrl: './superadmin.component.html',
  styleUrls: ['./superadmin.component.css'],
})
export class SuperadminComponent implements OnInit, OnDestroy {
  sidebarVisible: boolean = false;
  loading: boolean = false;
constructor(private navbarService:NavbarService,private footerService:FooterService){}
ngOnInit(): void {
    this.navbarService.hide();
    this.footerService.hide();
}
ngOnDestroy(): void {
    
}
  load() {
      this.loading = true;

      setTimeout(() => {
          this.loading = false
      }, 2000);
  }
}
