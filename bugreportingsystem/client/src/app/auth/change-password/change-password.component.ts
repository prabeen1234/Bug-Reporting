import { Component } from '@angular/core';
import { ChangePassword } from 'src/app/user';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangePasswordService } from '../service/change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  showPassword = false;

  public data: ChangePassword = {
    email: '',
    newPassword: '',
  };
constructor(private changePasswordService:ChangePasswordService,private toast:ToastrService,private formBuilder :FormBuilder){}

onSubmit() {
    this.changePasswordService.changePassword(this.data).subscribe({
      next:(response) => {
        this.toast.success('successfully changed');
        localStorage.setItem(
          'currentUser',
          JSON.stringify({ token: response, name: name })
        );
      },
      error:(error)=>{
           this.toast.error("user not found");
      }
      
    })
  }
}
