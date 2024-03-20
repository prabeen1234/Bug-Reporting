import { Component } from '@angular/core';
import { AdminRegisterService } from '../service/admin-register.service';
import { ToastrService } from 'ngx-toastr';
import { RegisterAdmin } from 'src/app/user';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css'],
})
export class RegisterAdminComponent {
  constructor(
    private adminRegisterService: AdminRegisterService,
    private toast: ToastrService
  ) {}
  public user: RegisterAdmin = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  };
  saveAdmin() {
    this.adminRegisterService.signup(this.user).subscribe({
      next: (response) => {
        this.toast.success('successfully register');
      },
      error: (error) => {
        this.toast.error(error);
        console.log('error');
      },
    });
  }
}
