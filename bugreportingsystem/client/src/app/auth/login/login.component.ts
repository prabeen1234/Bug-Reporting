import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from 'src/app/user';
import { SharedService } from 'src/app/shared/shared.service';
import { ToasterService } from '../service/toaster.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

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
        this.router.navigate(['']);
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
