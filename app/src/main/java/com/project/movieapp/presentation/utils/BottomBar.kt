package com.project.movieapp.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.movieapp.core.BottomNavItem
import com.project.movieapp.core.MultiStackNavigator
import com.project.movieapp.core.NavKey

@Composable
fun BottomBar(
    tabs: List<NavKey>,
    navigator: MultiStackNavigator<NavKey>
) {
    Surface(
        tonalElevation = 3.dp,
        shadowElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEach { tab ->
                val item = tab as BottomNavItem
                val selected = navigator.activeTab == tab

                val iconColor = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }

                val textColor = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }

                Column(
                    modifier = Modifier
                        .clickable { navigator.switchTab(tab) }
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = iconColor,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = item.title,
                        color = textColor,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
