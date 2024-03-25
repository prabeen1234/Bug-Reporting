// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SharedService } from '../shared/shared.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private sharedService: SharedService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.sharedService.loginStatus$.pipe(
      tap((loggedIn) => {
        if (!loggedIn) {
          this.router.navigate(['']);
        }
      })
    );
  }
}
