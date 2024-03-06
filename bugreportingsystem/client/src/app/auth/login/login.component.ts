import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from 'src/app/user';
import { SharedService } from 'src/app/shared/shared.service';
import { ToasterService } from '../service/toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  public data: LoginUser = {
    email: '',
    password: '',
    message:''
  };
  constructor(
    private loginService: LoginService,
    private toast: ToastrService,
    private sharedService: SharedService,
    private toasterservice:ToasterService
  ) {}
  onSubmit() {
    this.loginService.loginUser(this.data).subscribe({
      next: (response) => {
        this.toast.success(response.message);
        this.sharedService.setLoginStatuss(true);
        localStorage.setItem(
          'currentUser',
          JSON.stringify({ token: response, name: name })
        );
        
      },
      error: (err) => {
        if(err.status===401){
          this.toast.error(err.error.message)
        }
      },
    });
  }
}
