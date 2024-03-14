import { Component } from '@angular/core';

@Component({
  selector: 'app-superadmin',
  templateUrl: './superadmin.component.html',
  styleUrls: ['./superadmin.component.css'],
})
export class SuperadminComponent {
  sidebarVisible: boolean = false;
  loading: boolean = false;

  load() {
      this.loading = true;

      setTimeout(() => {
          this.loading = false
      }, 2000);
  }
}
