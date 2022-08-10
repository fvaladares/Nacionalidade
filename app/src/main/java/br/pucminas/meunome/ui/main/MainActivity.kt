/*
 * Created/Adapted by fabricio on 10/08/2022 05:51
 * Last modified 10/08/2022 05:37
 *
 * Copyright (C) 2022 Professor fabricio -- PUC Minas Virtual
 *
 * Code based on a example created by Google Developer Training
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package br.pucminas.meunome.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.meunome.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}