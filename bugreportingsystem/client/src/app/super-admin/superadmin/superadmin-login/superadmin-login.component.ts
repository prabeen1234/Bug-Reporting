import { Component, OnDestroy, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FooterService } from 'src/app/auth/service/footer.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';
import { LoginSuperAdmin } from 'src/app/superaddmin';
import { SuperadminLoginService } from './superadmin-login.service';
import { ButtonModule } from 'primeng/button';
@Component({
  selector: 'app-superadmin-login',
  templateUrl: './superadmin-login.component.html',
  styleUrls: ['./superadmin-login.component.css']
})
export class SuperadminLoginComponent implements OnInit,OnDestroy{
  public data: LoginSuperAdmin = {
    sadminEmail: '',
    sadminPassword: '',
  };
constructor(private superAdminLoginService:SuperadminLoginService,private toast:ToastrService,private navbarService:NavbarService,private footerService:FooterService){}
onSubmit() {
    this.superAdminLoginService.loginSuperAdmin(this.data).subscribe({
      next:(response) => {
        this.toast.success('successfully login');
        localStorage.setItem(
          'currentSuperAdmin',
          JSON.stringify({ token: response, name: name })
        );
      },
      error:(error)=>{
           this.toast.error("superadmin not found");
      }
      
    })
  }
  ngOnInit(): void {
      this.footerService.hide();
      this.navbarService.hide();
  }
  ngOnDestroy(): void {
      
  }

}
