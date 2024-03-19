import { Injectable, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { SharedService } from 'src/app/shared/shared.service';

@Injectable()
 export class PermissionsService {
   private router!:Router;
  private isLoggedIn = new BehaviorSubject<boolean>(false);
  loginStatus$ = this.isLoggedIn.asObservable();
  setLoginStatus(status: boolean): void {
    this.isLoggedIn.next(status);
  }

 canActivate(): boolean {
 if (status==='true') {
   return true;
 } else {
   this.router.navigate(['/login']);
   return false;
   }
 }

 }

export const childauthGuard: CanActivateFn = (route, state) => {
  return inject(PermissionsService).canActivate();
};
