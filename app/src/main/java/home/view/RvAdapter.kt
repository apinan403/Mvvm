package home.view
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm.R
import home.model.Entry
import kotlinx.android.synthetic.main.item_list.view.*

class RvAdapter(var context: Context) : RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    var productList: List<Entry> = listOf()
    private lateinit var itemClicklistener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(prodtid: String, tvProdtName: String)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        itemClicklistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view, itemClicklistener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardView.cardView
        holder.tvProdtId.text = productList[position].prodID
        holder.tvProdtName.text = productList[position].productName
        context.let {
            Glide.with(it).load(productList.get(position).images[0])
                .apply(RequestOptions().centerCrop())
                .into(holder.image_picture)
        }

    }

    fun setProdtListItems(productList: List<Entry>) {
        this.productList = productList;
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View, listner: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val tvProdtId: TextView = itemView.findViewById(R.id.tvProdtId)
        val tvProdtName: TextView = itemView.findViewById(R.id.tvProdtName)
        val image_picture: ImageView = itemView.findViewById(R.id.image_picture)

        init {
            itemView.setOnClickListener{ listner.onItemClick(itemView.tvProdtId.text as String, itemView.tvProdtName.text as String) }
        }

    }
}
