import {Component} from 'angular2/core';

@Component({
    selector: 'my-app',
    template: `
    	<div class="modal-header">
			<h4 class="modal-title">{{title}}</h4>
        </div>
    `
})

export class AppComponent {
    public title = 'Hello from JavaScript Client :)';
}
