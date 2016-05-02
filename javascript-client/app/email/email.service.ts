import {Injectable} from 'angular2/core';
import {Email} from './email.model';
import {Http, Response} from 'angular2/http';


@Injectable()
export class EmailService {
  constructor (private http: Http) {}

  private emailUrl = 'http://localhost:8080/emails';  // URL to web api
  
  getEmails (): Observable<Email[]> {
      return this.http.get(this.emailUrl)
                      .map(this.extractData)
                      .catch(this.handleError);
  }

  sendEmail (email Email) {
    var headers = new Headers();
    headers.append('Content-Type': 'application/json');

          this.http.post(this.emailUrl, JSON.stringify(email),{headers:headers})
          .map(res => res.json())
          .subscribe();
  }
  
  private extractData(res: Response) {
    if (res.status < 200 || res.status >= 300) {
      throw new Error('Bad response status: ' + res.status);
    }
    let body = res.json();
    return body || {};
  }
  
  private handleError (error: any) {
    // In a real world app, we might send the error to remote logging infrastructure
    let errMsg = error.message || 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}
