import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  goToLogin() {
    this.router.navigate(['/login']);
  }

  signupForm: FormGroup;
  selectedFile: File | null = null;

  constructor(private fb: FormBuilder, private signupService: AuthService, private router: Router) {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contactNo: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      postalCode: ['', Validators.required]
    });
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  onSubmit() {
    if (this.signupForm.invalid || !this.selectedFile) {
      alert('Please fill all fields and select an image.');
      return;
    }

    const formData = new FormData();
    
    const userData = {
      email: this.signupForm.value.email,
      name: this.signupForm.value.name,
      password: this.signupForm.value.password,
      roles: ["ADMIN"],
      contactNo: this.signupForm.value.contactNo,
      address: this.signupForm.value.address,
      postalCode: this.signupForm.value.postalCode
    };

    formData.append('data', JSON.stringify(userData));
    formData.append('adminImage', this.selectedFile);

    this.signupService.signup(formData).subscribe(
      (response) => {
        this.router.navigate(['/login']);
        alert('Signup successful!');
        console.log(response);
      },
      (error) => {
        alert('Signup failed. Please try again.');
        console.error(error);
      }
    );
  }
}
