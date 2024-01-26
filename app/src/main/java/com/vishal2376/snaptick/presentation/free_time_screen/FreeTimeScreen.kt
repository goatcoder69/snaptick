package com.vishal2376.snaptick.presentation.free_time_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.snaptick.domain.model.Task
import com.vishal2376.snaptick.presentation.common.h1TextStyle
import com.vishal2376.snaptick.presentation.common.h2TextStyle
import com.vishal2376.snaptick.presentation.common.taskTextStyle
import com.vishal2376.snaptick.presentation.free_time_screen.components.CustomPieChart
import com.vishal2376.snaptick.presentation.free_time_screen.components.PieChartItemComponent
import com.vishal2376.snaptick.ui.theme.SnaptickTheme
import com.vishal2376.snaptick.ui.theme.pieChartColors
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeTimeScreen(
	tasks: List<Task>,
	onBack: () -> Unit
) {

	val inCompletedTasks = tasks.filter { !it.isCompleted }
	val sortedTasks = inCompletedTasks.sortedBy { -it.getDuration() }
	val totalColors = pieChartColors.size

	Scaffold(topBar = {
		TopAppBar(
			modifier = Modifier.padding(8.dp),
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = "Analysis",
					style = h1TextStyle
				)
			},
			navigationIcon = {
				IconButton(onClick = { onBack() }) {
					Icon(
						imageVector = Icons.Rounded.ArrowBack,
						contentDescription = null
					)
				}
			},
		)
	}) { innerPadding ->

		Column(modifier = Modifier.padding(innerPadding)) {

			val pieChartBgGradient = Brush.verticalGradient(
				listOf(
					MaterialTheme.colorScheme.primary,
					MaterialTheme.colorScheme.secondary,
				)
			)
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						pieChartBgGradient,
						RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
					)
					.padding(bottom = 24.dp),
				contentAlignment = Alignment.Center
			) {
				val data = sortedTasks.map { it.getDuration() }
				CustomPieChart(data = data, pieChartSize = 180.dp)

				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "Free Time",
						color = Color.White,
						style = h2TextStyle
					)

					Text(
						text = "8 hours",
						color = Color.White,
						style = taskTextStyle
					)
				}
			}

			Spacer(modifier = Modifier.height(16.dp))

			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 16.dp)
			) {
				itemsIndexed(items = sortedTasks) { index, item ->
					PieChartItemComponent(
						task = item,
						itemColor = pieChartColors[index % totalColors],
						animDelay = 100 * index
					)
					Spacer(modifier = Modifier.height(10.dp))
				}
			}
		}
	}
}

@Preview
@Composable
fun FreeTimeScreenPreview() {
	val tasks = listOf(
		Task(
			id = 1,
			title = "Learn Kotlin",
			isCompleted = false,
			startTime = LocalTime.of(9, 0),
			endTime = LocalTime.of(10, 0),
			reminder = true,
			category = "",
			priority = 0
		),
		Task(
			id = 2,
			title = "Drink Water",
			isCompleted = false,
			startTime = LocalTime.of(9, 0),
			endTime = LocalTime.of(11, 0),
			reminder = false,
			category = "",
			priority = 1
		)
	)
	SnaptickTheme {
		FreeTimeScreen(tasks = tasks, {})
	}
}