## Description
[Function]
1. When Application ends due to an unexpected error, the log is sent by registered email.

2. E-mail the error that was caught in the catch of the try-catch syntax.

3. When communication fails, such as when there is no internet connection, the log can be saved in Shared Preferences, and the developer can try to send an email at the desired timing.

[Constraints]
1. The account to send the email is Gmail (any receiving account)

## Installaion
```implementeation```

## How to use
[Necessary]<br/>In the first code of the first screen (Activity or Fragment etc.) after the app is launched, create the following.
```
        CrashManager crashManager = new CrashManager(getApplicationContext());
        crashManager.setFromAccountAndPw("Sending account@gmail.com", "Sending account Password");
        crashManager.addToAccount("Receiving account@Any Email");
        crashManager.addToAccount("Receiving account@Any Email");
        ...
        ...
        crashManager.addToAccount("Receiving account@Any Email");
        crashManager.addToAccount("Receiving account@Any Email");
        Thread.setDefaultUncaughtExceptionHandler(crashManager);
```

[Options]
1. If you want to mail the logs stored in SharedPreferences that are not sent, create the following code.<br/>If you succeed in sending mail, all logs stored in SharedPreferences are deleted.
```
        new CrashSavedLogSend(getApplicationContext()).sendMailSavedCrashLog();
```

2. If you want to mail Error, which is caught by syntax, such as Try-Catch, write the following code.
```
        try {
                //your code
        } catch (Exception e) {
                new CrashCatchExceptionSend(getApplicationContext()).sendMailCatchException(e);
        }
```
