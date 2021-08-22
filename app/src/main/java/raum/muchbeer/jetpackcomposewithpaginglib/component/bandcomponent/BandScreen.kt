package raum.muchbeer.jetpackcomposewithpaginglib.component.bandcomponent

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import raum.muchbeer.jetpackcomposewithpaginglib.R
import raum.muchbeer.jetpackcomposewithpaginglib.model.*
import kotlin.random.Random

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun TodoScreen(
    items: List<SchoolModel>,
    currentlyEditing: SchoolModel?,
    onAddItem: (SchoolModel) -> Unit,
    onRemoveItem: (SchoolModel) -> Unit,
    onStartEdit: (SchoolModel) -> Unit,
    onEditItemChange: (SchoolModel) -> Unit,

) {
    Column {
        val enableTopSection = currentlyEditing == null
        TodoItemInputBackground(elevate = enableTopSection) {
            if (enableTopSection) {
                TodoItemEntryInputFirstOnTop(onAddItem)
            } else {
                Text(
                    text = "Editing item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)

        ) {
            items(items = items) { bandItem ->
                if (currentlyEditing?.id == bandItem.id) {
                    TodoItemInlineEditorInLazyColum(
                        item = currentlyEditing,
                        onEditItemChange = {  onEditItemChange(it) },
                        onRemoveItem = { onRemoveItem(bandItem) }
                    )
                } else {
                    TodoRow(
                        schoolModel = bandItem,
                        onItemClicked = { onStartEdit(bandItem) },
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }

        // For quick testing, a random item generator button
/*        Button(
            onClick = { onAddItem(SchoolModel("Loyola", R.drawable.ic_event))  },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add random item")
        }*/
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun TodoItemInlineEditorInLazyColum(
    item: SchoolModel,
    onEditItemChange: (SchoolModel) -> Unit,
    onRemoveItem: () -> Unit
) {

    val (text, onTextChange) = rememberSaveable { mutableStateOf(item.schoolname) }
    val (icon, onIconChange) = remember { mutableStateOf(item.icon) }


    val submit = {
        if (text.isNotBlank()) {
            onEditItemChange(SchoolModel(schoolname = text, icon = icon))
            }
    }

    TodoItemInput(
        text = text,
        onTextChange = {
                       onTextChange(it)
        },
        icon = icon,
        onIconChange = {
                       onIconChange(it)
        },
        submit = submit,
        iconsVisible = true,
        buttonSlot = {
            Row {
                val shrinkButtons = Modifier.widthIn(20.dp)
                TextButton(onClick = submit, modifier = shrinkButtons) {
                    Text(
                        "\uD83D\uDCBE", // floppy disk
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(30.dp)
                    )
                }
                TextButton(onClick = onRemoveItem, modifier = shrinkButtons) {
                    Text(
                        "❌",
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(30.dp)
                    )
                }
            }
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun TodoItemEntryInputFirstOnTop(onItemComplete: (SchoolModel) -> Unit, buttonText: String = "Add") {
    val (text, onTextChange) = rememberSaveable { mutableStateOf("") }
    val (icon, onIconChange) = remember { mutableStateOf(LocalIcon.Default.imageVector) }

    val submit = {
        if (text.isNotBlank()) {
            onItemComplete(SchoolModel(schoolname = text, icon = icon))
            onTextChange("")
            onIconChange(LocalIcon.Default.imageVector)
        }
    }

    TodoItemInput(
        text = text,
        onTextChange = onTextChange,
        icon = icon,
        onIconChange = onIconChange,
        submit = submit,
        iconsVisible = text.isNotBlank()
    ) {
        TodoEditButton(onClick = submit, text = buttonText, enabled = text.isNotBlank())
    }
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun TodoItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: Int,
    onIconChange: (Int) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean,
    buttonSlot: @Composable () -> Unit,
) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .height(IntrinsicSize.Min)
        ) {
            TodoInputText(
                text = text,
                onTextChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(Modifier.align(Alignment.CenterVertically)) { buttonSlot() }
        }
        if (iconsVisible) {
            AnimatedIconRow(
                icon = icon,
                onIconChange = onIconChange,
                modifier = Modifier.padding(top = 8.dp),
            )
        } else {
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun TodoRow(
    schoolModel: SchoolModel,
    onItemClicked: (SchoolModel) -> Unit,
    modifier: Modifier = Modifier,
    iconAlpha: Float = remember(schoolModel.id) { randomTint() }
) {
    Row(
        modifier
            .clickable { onItemClicked(schoolModel) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(schoolModel.schoolname)
        Icon(
            painter =  painterResource(id = schoolModel.icon) ,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription =  "option2 is coming"
        )
    }
}

private fun randomTint(): Float {  return Random.nextFloat().coerceIn(0.3f, 0.9f) }

