import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../core/services/user.service";
import {ToastService} from "../../../shared/toast/toast-service";
import Validation from "../../../shared/validations/Validation";
import {UserDetail} from "../../../shared/model/userDetail";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
  });
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private toast: ToastService) { }

  ngOnInit(): void {
    let user: UserDetail = this.userService.getLoggedInUser();
    this.form = this.formBuilder.group(
      {
        firstName: [user.firstName, Validators.required],
        lastName: [user.lastName, Validators.required],
        email: [user.email, [Validators.required, Validators.email]],
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onReset() {
    this.submitted = false;
    this.form.reset();
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }
  }
}
