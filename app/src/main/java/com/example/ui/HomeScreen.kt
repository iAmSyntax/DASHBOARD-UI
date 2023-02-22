package com.example.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.ui.theme.*

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingsMessage()
            ChipSection(chips = listOf("Hungry", "Sleepy", "Happy", "Depressed", "Insomnia"))
            CurrentMed()
            FeatureSection(
                features = listOf(
                    Features(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Features(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Features(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Features(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    Features(
                        title = "Control Your Anger",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    Features(
                        title = "Tips for fasting",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    )

                )
            )

        }
        ButtonMenu(mItems = listOf(
            BtmMenuCnt("Home", R.drawable.ic_home),
            BtmMenuCnt("Meditate",R.drawable.ic_bubble),
            BtmMenuCnt("Sleep", R.drawable.ic_moon),
            BtmMenuCnt("Music", R.drawable.ic_music),
            BtmMenuCnt("Profile",R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun ButtonMenu(
    mItems : List<BtmMenuCnt>,
    modifier: Modifier = Modifier,
    highlightClrBtn : Color = ButtonBlue,
    highlightClrText: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    initialIndex :Int =0
){
 var currentIndex by remember {
     mutableStateOf(initialIndex)
 }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        mItems.forEachIndexed { index, icons ->  
            BottomMenuItem(
                item = icons,
                isSelected = (index == currentIndex),
                highlightClrBtn = highlightClrBtn,
                highlightClrText = highlightClrText,
                inActiveTextColor = inActiveTextColor
            ) {
                currentIndex = index
            }
        }
    }
}


@Composable
fun BottomMenuItem(
    item : BtmMenuCnt,
    isSelected :Boolean = false,
    highlightClrBtn : Color = ButtonBlue,
    highlightClrText: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    onItemClick : () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) highlightClrBtn else Color.Transparent)
                .padding(10.dp)
                ){
                Icon(
                    painter = painterResource(id = item.icnId),
                    contentDescription = item.tittle,
                    tint =  if(isSelected) highlightClrText else inActiveTextColor,
                    modifier = Modifier.size(20.dp)
                )

        }

        Text(
            text = item.tittle,
            color = if(isSelected) highlightClrText else inActiveTextColor
        )




    }

}

@Composable
fun GreetingsMessage(
    name: String = "Mariyam"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome $name",
                style = TextStyle(
                    color = TextWhite,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            Text(
                text = "Have a nice day",
                style = TextStyle(
                    color = AquaBlue,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )


    }
}


@Composable
fun ChipSection(
    chips: List<String>
) {

    var selectedChip by remember { mutableStateOf(0) }

    LazyRow {

        items(chips.size) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 15.dp)
                    .clickable {
                        selectedChip = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChip == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }

    }

}


@Composable
fun CurrentMed(
    clr: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 15.dp, top = 20.dp, bottom = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(clr)
            .padding(horizontal = 15.dp, vertical = 25.dp)
            .fillMaxWidth()
    ) {

        Column {
            Text(
                text = "Daily Thought",
                style = TextStyle(
                    color = TextWhite,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            Text(
                text = "Lets Meditate",
                style = TextStyle(
                    color = TextWhite,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )

        }
        
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = " Play ",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(
    features:List<Features>
){
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style =TextStyle(
                color = TextWhite,
                fontFamily = gothicA1,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            ),
            modifier = Modifier.padding(15.dp)

        )
        LazyVerticalGrid(
            cells =GridCells.Fixed(2) ,
            contentPadding = PaddingValues(start=7.5.dp,end=7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()){
            items(features.size){
                FeatureItem(f =features[it])
            }
        }


    }

}

@Composable
fun FeatureItem(
    f : Features
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(f.darkClr)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight


        // medium clr
        val medP1 = Offset(0f,height*0.3f)
        val medP2 = Offset(width*0.1f,height*0.35f)
        val medP3 = Offset(width*0.4f,height*0.05f)
        val medP4 = Offset(width*0.75f,height*0.7f)
        val medP5 = Offset(width*1.4f,-height.toFloat())

        val mediumClrPath = Path().apply {
            moveTo(medP1.x,medP1.y)
            standardQuadFromTo(medP1,medP2)
            standardQuadFromTo(medP2,medP3)
            standardQuadFromTo(medP3,medP4)
            standardQuadFromTo(medP4,medP5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }

        // light clr
        val lightP1 = Offset(0f,height*0.35f)
        val lightP2 = Offset(width*0.1f,height*0.4f)
        val lightP3 = Offset(width*0.3f,height*0.35f)
        val lightP4 = Offset(width*0.65f,height.toFloat())
        val lightP5 = Offset(width*1.4f,-height.toFloat()/3f)

        val lightClrPath = Path().apply {
            moveTo(lightP1.x,lightP1.y)
            standardQuadFromTo(lightP1,lightP2)
            standardQuadFromTo(lightP2,lightP3)
            standardQuadFromTo(lightP3,lightP4)
            standardQuadFromTo(lightP4,lightP5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }


        Canvas(
            modifier =Modifier.fillMaxSize()
        ){
            drawPath(path = mediumClrPath, color = f.medClr)
            drawPath(path = lightClrPath, color = f.lightClr)
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = f.title,
                style = TextStyle(
                    color = TextWhite,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = f.iconId),
                contentDescription = f.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }





    }

}