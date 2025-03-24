import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = { email: '', password: '' };

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (!this.loginData.email || !this.loginData.password) {
      alert('Please enter both email and password');
      return;
    }

    this.authService.login(this.loginData).subscribe(
      (response) => {
        console.log('Login successful:', response);
        localStorage.setItem('token', response.token); // Store token
        this.router.navigate(['/profile']); // Navigate to profile page
      },
      (error) => {
        console.error('Login failed:', error);
        alert('Invalid email or password. Please try again.');
      }
    );
  }
}
