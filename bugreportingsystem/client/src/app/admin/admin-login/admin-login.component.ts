import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from 'src/app/auth/service/login.service';
import { SharedService } from 'src/app/shared/shared.service';
import { LoginUser } from 'src/app/user';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
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
    private toasterservice: ToastrService,
    private jwtHelper: JwtHelperService
  ) {}
  ngOnInit(): void {
    this.getToken();
  }

  onSubmit() {
    this.loginService.loginUser(this.data).subscribe({
      next: (response) => {
        this.toast.success(response.message);
        localStorage.setItem(
          'token',
          JSON.stringify({ token: response, name: name })
        );
        this.sharedService.setLoginStatus(true);
        this.router.navigate(['adminbug']);
      },
      error: (err) => {
        if (err.status === 401) {
          this.toast.error(err.error.message);
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