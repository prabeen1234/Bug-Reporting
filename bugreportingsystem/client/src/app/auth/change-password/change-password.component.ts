import { Component, OnInit } from '@angular/core';
import { ChangePassword } from 'src/app/user';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangePasswordService } from '../service/change-password.service';
import { AdminbarService } from 'src/app/admin/admin/adminbar.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  showPassword = false;

  public data: ChangePassword = {
    email: '',
    oldPassword:'',
    newPassword: '',
    confirmPassword:''
  };
constructor(private changePasswordService:ChangePasswordService,private toast:ToastrService,private formBuilder :FormBuilder,private adminbarService:AdminbarService) {}
ngOnInit(): void {
    this.adminbarService.hide();
}
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
