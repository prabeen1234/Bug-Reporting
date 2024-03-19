import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  
  private isLoggedIn = new BehaviorSubject<boolean>(false);
  loginStatus$ = this.isLoggedIn.asObservable();
  setLoginStatus(status: boolean): void {
    this.isLoggedIn.next(status);
    console.log("shared service"+status);
  }
  
  constructor() { }
  
  
}
