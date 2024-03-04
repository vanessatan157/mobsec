import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class usrbooking {
    @Composable
    fun Usrbooking() {
        android.view.Surface(color = Color.White) {
            ConstraintLayoutContent()
        }
    }

    @Composable
    fun ConstraintLayoutContent() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp, start = 10.dp, end = 10.dp)
        ) {
            val (tvUserGreeting, tvSchedAppointment, btnDoctorName, btnDate, btnTime, btnCatVisit, btnSubmit) = createRefs()

            BasicText(
                text = "Welcome, user!",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.constrainAs(tvUserGreeting) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            BasicText(
                text = "Schedule Appointment",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.constrainAs(tvSchedAppointment) {
                    top.linkTo(tvUserGreeting.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier
                    .constrainAs(btnDoctorName) {
                        top.linkTo(tvSchedAppointment.bottom, margin = 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
            ) {
                BasicText(text = "Doctor Name")
            }

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier
                    .constrainAs(btnDate) {
                        top.linkTo(btnDoctorName.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
            ) {
                BasicText(text = "Date")
            }

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier
                    .constrainAs(btnTime) {
                        top.linkTo(btnDate.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
            ) {
                BasicText(text = "Time")
            }

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier
                    .constrainAs(btnCatVisit) {
                        top.linkTo(btnTime.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
            ) {
                BasicText(text = "Category of Visit")
            }

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier
                    .constrainAs(btnSubmit) {
                        top.linkTo(btnCatVisit.bottom, margin = 30.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            ) {
                BasicText(text = "Submit")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewusrBooking() {
        Usrbooking()
    }
}