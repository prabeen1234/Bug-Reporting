import { Injectable } from '@angular/core';
import { BehaviorSubject, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminbarService {
  showadminBar: BehaviorSubject<boolean>;

  constructor() {
    this.showadminBar = new BehaviorSubject(true);
  }
  hide() {
    this.showadminBar.next(false);
  }
  show() {
    this.showadminBar.next(true);
  }
}
