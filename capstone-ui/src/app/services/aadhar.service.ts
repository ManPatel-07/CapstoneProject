import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface AadharData {
  aadharName: string;
  aadharNo: string;
  aadharImage: string;
}

@Injectable({
  providedIn: 'root'
})
export class AadharService {
  private apiUrl = 'http://localhost:8081/api/v1/addAadharData2';
  // private authToken = 'your-auth-token-here'; // Replace with actual token

  constructor(private http: HttpClient) {}

  uploadAadhar(data: any, imageFile: File): Observable<any> {
    const formData = new FormData();
    formData.append('data', JSON.stringify(data)); // JSON data
    formData.append('aadharImage', imageFile); // File upload

    // const headers = new HttpHeaders({
    //   Authorization: `Bearer ${this.authToken}`,
    // });

    console.log(imageFile)

    return this.http.post(this.apiUrl, formData);
  }
}
