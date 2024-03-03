import { Component } from '@angular/core';
import { ChangePassword } from 'src/app/user';
import { ChangePasswordService } from '../service/change-password.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent {
  public data: ChangePassword = {
    email: '',
    newPassword: '',
  };
  constructor(
    private changePasswordService: ChangePasswordService,
    private toast: ToastrService
  ) {}
  onSubmit() {
    this.changePasswordService.changePassword(this.data).subscribe({
      next: (response) => {
        this.toast.success('successfully changed');
        localStorage.setItem(
          'currentUser',
          JSON.stringify({ token: response, name: name })
        );
      },
      error: (error) => {
        this.toast.error('user not found');
      },
    });
  }
}
