import { Component } from '@angular/core';
import { AdminBugDto } from 'src/app/user';
import { AdminService } from '../admin.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin-bug',
  templateUrl: './admin-bug.component.html',
  styleUrls: ['./admin-bug.component.css']
})
export class AdminBugComponent {
  public bugs: AdminBugDto[] = [];
  public selectedBug: AdminBugDto | null = null;

  constructor(
    private adminService: AdminService,
    private toast: ToastrService
  ) {}
  ngOnInit(): void {
    this.getBugByAdmin();
  }

  getBugByAdmin() {
    this.adminService.getBugOfUser().subscribe({
      next: (bugs) => {
        this.bugs = bugs.map(bug => ({ ...bug, showDetails: false }));

      },
      error: (err) => {},
    });
  }
  selectCard(bug: AdminBugDto) {
    this.selectedBug = bug;
  }
  clearSelection() {
    this.selectedBug = null;
  }
  toggleDetails(bug: AdminBugDto): void {
    bug.showDetails = !bug.showDetails;
  }
}
