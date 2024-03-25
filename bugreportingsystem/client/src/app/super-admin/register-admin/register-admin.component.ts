import { Component, OnInit } from '@angular/core';
import { AdminRegisterService } from '../service/admin-register.service';
import { ToastrService } from 'ngx-toastr';
import { RegisterAdmin } from 'src/app/user';
import { FooterService } from 'src/app/auth/service/footer.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css'],
})
export class RegisterAdminComponent implements OnInit{
  constructor(
    private adminRegisterService: AdminRegisterService,
    private toast: ToastrService,
    private footerService:FooterService,
    private navbarService:NavbarService
  ) {}
  
  public user: RegisterAdmin = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  };

  saveAdmin() {
    this.adminRegisterService.adminsignup(this.user).subscribe({
      next: (response) => {
        this.toast.success('Admin successfully registered');
      },
      error: (error) => {
        this.toast.error(error.message);
        console.error(error);
      },
    });
  }
  ngOnInit(): void {
      this.navbarService.hide();
      this.footerService.hide();
  }
}
