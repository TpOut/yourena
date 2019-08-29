```

public class IntentUtil {

    public static void startSysIntent(Context context) {

        /**
         * 电话、phonecall
         */
        Uri number = Uri.parse("tel:5551234");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        /**
         * 地图、map
         */
        // Map point based on address
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        // Or map point based on latitude/longitude
        // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

        /**
         * 网页、web
         */
        Uri webpage = Uri.parse("http://www.baidu.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
//            startActivity(webIntent);
        }

        /**
         * 上述方式打开带有默认选择的窗口
         * 如果想每次都可以选择，则以下述方式打开
         */
        Intent intent = new Intent(Intent.ACTION_SEND);
        // Create intent to show chooser
        Intent chooser = Intent.createChooser(intent, "对话框的标题");
        // Verify the intent will resolve to at least one activity
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            //startActivity(chooser);
        }

        /**
         * 发送带附件的电子邮件：failed
         */
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
//        emailIntent.setType()
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
    }
}   
```
