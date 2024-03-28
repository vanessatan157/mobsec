import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

object SmsReader {
    fun readSms(context: Context): List<SmsMessage> {
        val smsList = mutableListOf<SmsMessage>()

        // Query the SMS content provider to retrieve SMS messages
        val cursor = context.contentResolver.query(
            Uri.parse("content://sms/inbox"),
            null,
            null,
            null,
            null
        )

        cursor?.use { c ->
            while (c.moveToNext()) {
                val sender = c.getString(c.getColumnIndexOrThrow("address"))
                val messageBody = c.getString(c.getColumnIndexOrThrow("body"))
                val timestamp = c.getLong(c.getColumnIndexOrThrow("date"))

                // Create SmsMessage object and add it to the list
                val smsMessage = SmsMessage(sender, messageBody, timestamp)
                smsList.add(smsMessage)
            }
        }

        return smsList
    }
}

data class SmsMessage(
    val sender: String,
    val messageBody: String,
    val timestamp: Long
)

@Composable
fun SmsListPreview() {
    val context = LocalContext.current
    val smsList = SmsReader.readSms(context)

    SmsList(smsList)
}

@Composable
fun SmsList(smsList: List<SmsMessage>) {
    LazyColumn {
        items(smsList) { sms ->
            SmsListItem(sms)
        }
    }
}

@Composable
fun SmsListItem(sms: SmsMessage) {
    Column {
        Text(text = "Sender: ${sms.sender}")
        Text(text = "Message: ${sms.messageBody}")
        Text(text = "Timestamp: ${sms.timestamp}")
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSmsList() {
    val sampleSmsList = listOf(
        SmsMessage("Sender 1", "Hello", System.currentTimeMillis()),
        SmsMessage("Sender 2", "Hi there!", System.currentTimeMillis())
    )
    SmsList(sampleSmsList)
}
