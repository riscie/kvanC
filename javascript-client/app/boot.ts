import {bootstrap}    from 'angular2/platform/browser'
import {EmailComponent} from './email/email.component';
import {HTTP_PROVIDERS} from 'angular2/http';

bootstrap(EmailComponent, HTTP_PROVIDERS);