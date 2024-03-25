import { Component, OnDestroy, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FooterService } from 'src/app/auth/service/footer.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';
import { LoginSuperAdmin } from 'src/app/superaddmin';
import { SuperadminLoginService } from './superadmin-login.service';
import { ButtonModule } from 'primeng/button';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginService } from 'src/app/auth/service/login.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Router } from '@angular/router';
import { LoginUser } from 'src/app/user';
@Component({
  selector: 'app-superadmin-login',
  templateUrl: './superadmin-login.component.html',
  styleUrls: ['./superadmin-login.component.css']
})
export class SuperadminLoginComponent implements OnInit{
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
  ) {}

  ngOnInit(): void {
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
