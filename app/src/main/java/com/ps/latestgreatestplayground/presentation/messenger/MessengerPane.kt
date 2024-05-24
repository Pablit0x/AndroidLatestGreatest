package com.ps.latestgreatestplayground.presentation.messenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.Send
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ps.latestgreatestplayground.R

@Composable
fun MessengerPane(modifier: Modifier = Modifier) {
    val viewModel = viewModel<MessengerViewModel>()
    val uiState by viewModel.messengerUiState.collectAsState()

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.messages) { message ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (message.isReceived) Alignment.CenterStart else Alignment.CenterEnd
                ) {
                    Column(horizontalAlignment = if (message.isReceived) Alignment.Start else Alignment.End) {
                        ElevatedCard(
                            colors = CardDefaults.elevatedCardColors()
                                .copy(containerColor = if (message.isReceived) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(
                                text = message.text,
                                modifier = Modifier
                                    .padding(8.dp),
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = if (message.isReceived) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary,
                                    textAlign = if (message.isReceived) TextAlign.Start else TextAlign.End
                                )
                            )
                        }

                        Text(
                            text = message.formattedTime,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )

                    }
                }
            }
        }
        Box(modifier = modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = uiState.currentMessageValue,
                    onValueChange = {
                        viewModel.onAction(
                            action = MessengerAction.MessageValueChanged(
                                it
                            )
                        )
                    },
                    placeholder = { Text(text = stringResource(R.string.message_placeholder)) },
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { viewModel.onAction(action = MessengerAction.MessageSent) }) {
                    Icon(imageVector = Icons.AutoMirrored.Sharp.Send, contentDescription = null)
                }
            }
        }
    }

}
