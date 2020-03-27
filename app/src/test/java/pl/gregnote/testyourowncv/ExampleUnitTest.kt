package pl.gregnote.testyourowncv

import android.content.Context
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import pl.gregnote.testyourowncv.di.component.FragmentComponent
import pl.gregnote.testyourowncv.di.module.FragmentModule
import pl.gregnote.testyourowncv.models.Item
import pl.gregnote.testyourowncv.ui.fragment.list.ListPresenter
import pl.gregnote.testyourowncv.ui.list.ListContract

open class ListPresenterUnitTest {

    @Mock
    lateinit var mockComponent: FragmentComponent

    @Mock
    lateinit var mockModule: FragmentModule

    @Mock
    lateinit var mockApp: BaseApp

    @Mock
    lateinit var mockContext: Context

    @Mock
    lateinit var mockView: ListContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldCreateANonNullMockedPresenter() {
        //given
        val presenter = givenAMockedPresenter()

        //when

        //then
        assertNotNull(presenter)
    }

    @Test
    fun shouldShowProgressbarOnStart() {
        //given
        val presenter = givenAMockedPresenter()

        //when
        presenter.requestDataFromServer()

        //then
        Mockito.verify(mockView, Mockito.times(1)).showProgress()
    }

    @Test
    fun shouldCallSetDataToRecyclerViewOnStart() {
        //given
        val presenter = givenAMockedPresenter()

        //when
        presenter.requestDataFromServer()

        //then
        Mockito.verify(mockView, Mockito.times(1)).setDataToRecyclerView(ArgumentMatchers.any(ArrayList<Item>()::class.java))
    }

    fun givenAMockedEnvironment() {
        Mockito.`when`(mockContext.applicationContext).thenReturn(mockApp)
        Mockito.`when`(mockApp.fragmentComponent).thenReturn(mockComponent)
    }

    fun givenAMockedPresenter(): ListPresenter {
        givenAMockedEnvironment()
        val presenter = ListPresenter()
        presenter.attach(mockView)
        return presenter
    }
}
