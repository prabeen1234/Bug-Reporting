import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BugService } from '../bug.service';
import { ToastrService } from 'ngx-toastr';
import { BugDto } from 'src/app/user';

@Component({
  selector: 'app-bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.css'],
})
export class BugComponent {
  photo!: File;
  video!: File;

  bugReportFormGroup!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private bugservice: BugService,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.bugReportFormGroup = this.formBuilder.group({
      bugTitle: ['', Validators.required],
      bugDescription: ['', Validators.required],
      photo
    });
  }
  addBugByUser(): void {
    debugger;
    const file = this.bugReportFormGroup.value;
    const bugObj: BugDto = {
      bugTitle: this.bugReportFormGroup.get('bugTitle')?.value,
      bugDescription: this.bugReportFormGroup.get('bugDescription')?.value,
    };

    this.bugservice.addUserBug(bugObj, this.photo, this.video).subscribe({
      next: (response) => {},
      error: (err) => {},
    });
  }
}
// onFileChange(event: any): void {
//   this.selectedFile = event.target.files[0];
// }
