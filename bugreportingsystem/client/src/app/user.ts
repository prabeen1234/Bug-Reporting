export interface RegisterUser{
    firstName: string;
    lastName:string;
    email:string;
    password:string;
    }
    export interface RegisterAdmin{
        firstName: string;
        lastName:string;
        email:string;
        password:string;
        }
    export interface LoginUser{
        email:string;
        password:string;
        message:string;
    }
   
    export interface ChangePassword{
        email:string;
        newPassword:string;
    }

    export interface BugDto{
        bugTitle: string;
        bugDescription: string;
    }

    export interface AdminBugDto{
        bugTitle: string;
        bugDescription: string;
        photo: string; 
        video: string;
        email:string;
        showDetails:boolean
     

    }
    