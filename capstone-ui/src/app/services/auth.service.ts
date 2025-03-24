import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/auth/signup2';

  constructor(private http: HttpClient) {}

  signup(formData: FormData): Observable<any> {
    return this.http.post(this.apiUrl, formData);
  }

  private loginUrl = 'http://localhost:8081/auth/login';

  login(credentials: any): Observable<any> {
    return this.http.post(this.loginUrl, credentials);
  }
  
}
