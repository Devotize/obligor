package com.example.obligor.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.obligor.domain.models.Promiser
import com.example.obligor.ui.dimens.Dimens
import kotlinx.coroutines.flow.Flow

@Composable
fun PromiserSelectionScreen(
    selectedPromiser: Flow<Promiser>,
    allPromisers: Flow<List<Promiser>>,
    onPromiserClick: (Promiser) -> Unit,
) {

    val _allPromisers = allPromisers.collectAsState(initial = emptyList())
    val _selectedPromiser = selectedPromiser.collectAsState(initial = Promiser.EmptyPromiser)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(_allPromisers.value) {
            PromiserCard(
                promiser = it,
                isSelected = it == _selectedPromiser.value,
                onClick = {
                    onPromiserClick.invoke(it)
                }
            )
        }
    }
}

@Composable
private fun PromiserCard(
    promiser: Promiser,
    isSelected: Boolean,
    onClick: (Promiser) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.paddingMedium)
                .clickable {
                    onClick.invoke(promiser)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = Dimens.paddingMedium, bottom = Dimens.paddingMedium),
                text = promiser.name,
                style = MaterialTheme.typography.titleMedium
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        ),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(top = 1.dp, start = Dimens.paddingSmall, end = Dimens.paddingSmall)
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = ShapeDefaults.Small
                )
        )
    }
}