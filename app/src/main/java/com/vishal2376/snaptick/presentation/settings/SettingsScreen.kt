package com.vishal2376.snaptick.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.snaptick.R
import com.vishal2376.snaptick.presentation.common.AppTheme
import com.vishal2376.snaptick.presentation.common.h1TextStyle
import com.vishal2376.snaptick.presentation.common.infoDescTextStyle
import com.vishal2376.snaptick.presentation.settings.common.SettingCategoryItem
import com.vishal2376.snaptick.presentation.settings.components.SettingsCategoryComponent
import com.vishal2376.snaptick.ui.theme.SnaptickTheme
import com.vishal2376.snaptick.util.Constants
import com.vishal2376.snaptick.util.openUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
	val context = LocalContext.current

	val settingsAbout = listOf(
		SettingCategoryItem(title = "About", resId = R.drawable.ic_info),
		SettingCategoryItem(title = "Support", resId = R.drawable.ic_support),
	)

	val settingsGeneral = listOf(
		SettingCategoryItem(title = "Theme", resId = R.drawable.ic_theme),
		SettingCategoryItem(title = "Language", resId = R.drawable.ic_translate),
		SettingCategoryItem(title = "Sleep Time", resId = R.drawable.ic_moon),
		SettingCategoryItem(title = "Time Picker", resId = R.drawable.ic_clock),
	)

	val settingsFollow = listOf(
		SettingCategoryItem(
			title = "Twitter",
			resId = R.drawable.ic_twitter,
			onClick = { openUrl(context, Constants.TWITTER) }
		),
		SettingCategoryItem(
			title = "Github",
			resId = R.drawable.ic_github,
			onClick = { openUrl(context, Constants.GITHUB) }
		),
		SettingCategoryItem(
			title = "LinkedIn",
			resId = R.drawable.ic_linkedin,
			onClick = { openUrl(context, Constants.LINKEDIN) }
		),
		SettingCategoryItem(
			title = "Instagram",
			resId = R.drawable.ic_instagram,
			onClick = { openUrl(context, Constants.INSTAGRAM) }
		),
	)

	Scaffold(topBar = {
		TopAppBar(
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = stringResource(R.string.settings),
					style = h1TextStyle
				)
			},
			navigationIcon = {
				IconButton(onClick = { }) {
					Icon(
						imageVector = Icons.Rounded.ArrowBack,
						contentDescription = null
					)
				}
			},
		)
	}) { innerPadding ->
		Column(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize(),
			verticalArrangement = Arrangement.SpaceBetween,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Column(
				modifier = Modifier.verticalScroll(rememberScrollState()),
				verticalArrangement = Arrangement.spacedBy(24.dp)
			) {
				SettingsCategoryComponent(
					categoryTitle = "",
					categoryList = settingsAbout
				)
				SettingsCategoryComponent(
					categoryTitle = stringResource(R.string.general_settings),
					categoryList = settingsGeneral
				)
				SettingsCategoryComponent(
					categoryTitle = stringResource(R.string.follow_developer),
					categoryList = settingsFollow
				)
			}

			Text(
				modifier = Modifier.padding(8.dp),
				text = stringResource(R.string.made_with_by_vishal_singh),
				style = infoDescTextStyle,
				color = MaterialTheme.colorScheme.onSecondary
			)
		}
	}
}

@Preview
@Composable
fun SettingsScreenPreview() {
	SnaptickTheme(theme = AppTheme.Amoled) {
		SettingsScreen()
	}
}