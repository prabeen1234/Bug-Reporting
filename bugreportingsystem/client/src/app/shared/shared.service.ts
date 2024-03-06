import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  
  private loginStatusSubject = new BehaviorSubject<boolean>(false);
  loginStatus$ = this.loginStatusSubject.asObservable();
  setLoginStatuss(status: boolean): void {
    this.loginStatusSubject.next(status);
    console.log("shared service"+status);
  }
  constructor() { }
  
}
