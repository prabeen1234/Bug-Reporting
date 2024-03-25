// import { Component, OnInit } from '@angular/core';
// import { LoginService } from '../service/login.service';
// import { ToastrService } from 'ngx-toastr';
// import { LoginUser } from 'src/app/user';
// import { SharedService } from 'src/app/shared/shared.service';
// import { ToasterService } from '../service/toaster.service';
// import { Router } from '@angular/router';
// import { AdminbarService } from 'src/app/admin/admin/adminbar.service';
// import {JwtHelperService} from "@auth0/angular-jwt";

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css'],
// })
// export class LoginComponent implements OnInit {
//   key!: string;
//   public data: LoginUser = {
//     email: '',
//     password: '',
//     message: ''
//     };
//   constructor(
//     private loginService: LoginService,
//     private toast: ToastrService,
//     private sharedService: SharedService,
//     private router: Router,
//     private toasterservice: ToasterService,
//     private jwtHelper: JwtHelperService,
//     private adminbarService:AdminbarService
//   ) {}
//   ngOnInit(): void {

//     this.adminbarService.hide();

//   }

//   onSubmit() {

//     this.loginService.loginUser(this.data).subscribe({
//       next: (response: any) => {
//         this.toast.success(response.message);
//         console.log(response);
//         localStorage.setItem('token', response.token);
//         this.sharedService.setLoginStatus(true);
//         let token = localStorage.getItem('token');

//         const jwtHelper = new JwtHelperService();
//         const myTokenData = jwtHelper.decodeToken(token!);
//         const role = myTokenData.role;
//         console.log(role);

//       },
//       error: (err) => {
//         if (err.status === 401) {
//           this.toast.error(err.error.message);
//         }
//       },
//     });
//   }

// }
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from 'src/app/user';
import { SharedService } from 'src/app/shared/shared.service';
import { ToasterService } from '../service/toaster.service';
import { Router } from '@angular/router';
import { AdminbarService } from 'src/app/admin/admin/adminbar.service';
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
    private jwtHelper: JwtHelperService,
    private adminbarService: AdminbarService
  ) {}

  ngOnInit(): void {
    this.adminbarService.hide();
  }

  onSubmit() {
    this.loginService.loginUser(this.data).subscribe({
      next: (response: any) => {
        this.toast.success(response.message);
        console.log(response);
        localStorage.setItem('token', response.token);
        this.sharedService.setLoginStatus(true);
        let token = localStorage.getItem('token');

        const jwtHelper = new JwtHelperService();
        const myTokenData = jwtHelper.decodeToken(token!);
        const role = myTokenData.role;

        if (role === 'ADMIN') {
          this.router.navigate(['admin']);
        } else if (role === 'USER') {
          this.router.navigate(['']);
        } else {
          this.toast.error('Please register first......');
          this.router.navigate(['/login']);
        }
      },
      error: (err) => {
        if (err.status === 401) {
          this.toast.error(err.error.message);
        }
      },
    });
  }
}
