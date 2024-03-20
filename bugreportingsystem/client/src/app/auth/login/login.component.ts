import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from 'src/app/user';
import { SharedService } from 'src/app/shared/shared.service';
import { ToasterService } from '../service/toaster.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AdminbarService } from 'src/app/admin/admin/adminbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  key!: string;
  public data: LoginUser = {
    email: '',
    password: '',
    message: '',
  };
  constructor(
    private loginService: LoginService,
    private toast: ToastrService,
    private sharedService: SharedService,
    private router: Router,
    private toasterservice: ToasterService,
    private jwtHelper: JwtHelperService,
    private adminbarService:AdminbarService
  ) {}
  ngOnInit(): void {
    this.getToken();
    this.adminbarService.hide();
  }

  onSubmit() {
    this.loginService.loginUser(this.data).subscribe({
      next: (response: any) => {
        const token = response.token; // Assuming the token property is directly available in the response object
        localStorage.setItem('token', token);
        this.sharedService.setLoginStatus(true);
        this.router.navigate(['']);
        
        // Success toast message for successful login
        this.toast.success('Login successful');
      },
      error: (err) => {
        if (err.status === 401) {
          this.toast.error(err.error.message);
        } else {
          // Report bug successfully toast message
          this.toast.success('Bug reported successfully');
        }
      },
    });
  }
  getToken() {
    const token = localStorage.getItem('currentUser');
    console.log(token)
    if(token){
      const decodedToken = this.jwtHelper.decodeToken(token);
      const email = decodedToken['sub'];
      console.log(email)
    }
  }
}
